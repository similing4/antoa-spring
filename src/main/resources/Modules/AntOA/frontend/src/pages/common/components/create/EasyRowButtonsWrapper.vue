<template>
	<div>
		<a-button @click="onRowButtonClick(rowButton, record, index)" :type="rowButton.buttonType" v-for="(rowButton,index) in gridListObject.listRowButtonCollection" :key="index" v-if="record['BUTTON_CONDITION_DATA'][index]" style="margin: 5px;">
			{{ rowButton.buttonText }}
		</a-button>
		<a-button type="primary" @click="$emit('select')">选择</a-button>
		<a-modal v-model="richHtmlModal.isShow" @ok="richHtmlModal.isShow = false">
			<div v-html="richHtmlModal.html"></div>
		</a-modal>
		<confirm-dialog ref="confirmDialog"></confirm-dialog>
	</div>
</template>
<script>
import confirmDialog from "@/components/tool/ConfirmDialog.vue";
export default {
	props: {
		gridListObject: {
			type: Object,
			default () {
				return {
					"primaryKey": "id",
					"listFilterCollection": [], //{"type": "ListFilterText","col": "name","tip": "比赛名称"}
					"listTableColumnCollection": [], //{"type": "ListTableColumnText","col": "id","tip": "ID"}
					"listHeaderButtonCollection": [],
					"listRowButtonCollection": [], //{"type": "ListRowButtonNavigate","buttonText": "报名记录","buttonType": "primary","baseUrl": "\/race\/register\/list","finalUrl": null}
					"hasCreate": false,
					"hasEdit": false,
					"hasDelete": false
				}
			}
		},
		gridApiObject: {
			type: Object,
			default () {
				return {
					api_column_change: "",
					create: "",
					create_page: "",
					delete: "",
					detail: "",
					detail_column_list: "",
					edit_page: "",
					list: "",
					list_page: "",
					path: "",
					save: ""
				}
			}
		},
		record: {
			type: Object,
			default () {
				return {
					BUTTON_CONDITION_DATA: [],
					BUTTON_FINAL_URL_DATA: []
				}
			}
		}
	},
	data() {
		return {
			richHtmlModal: {
				isShow: false,
				html: ""
			}
		};
	},
	components:{
		confirmDialog
	},
	methods: {
		loadPage() {
			this.$emit("loadpage");
		},
		async onRowButtonClick(rowButtonItem, record, index) {
			let finalUrl = this.record.BUTTON_FINAL_URL_DATA[index];
			let param = {
				query: param,
				row: record
			};
			if (rowButtonItem.type === "ListRowButtonApi") {
				let res = await this.$api(finalUrl).method("POST").param(param).call();
				if (!res.status)
					this.$message.error(res.msg);
				else
					this.$message.success(res.data);
				this.loadPage();
			} else if (rowButtonItem.type === "ListRowButtonApiWithConfirm") {
				this.$refs.confirmDialog.confirm("确认要这样做么？").then(async () => {
					let res = await this.$api(finalUrl).method("POST").param(param).call();
					if (!res.status)
						this.$message.error(res.msg);
					else
						this.$message.success(res.data);
					this.loadPage();
				});
			} else if (rowButtonItem.type === "ListRowButtonNavigate") {
				this.$emit('openurl', finalUrl);
			} else if (rowButtonItem.type === "ListRowButtonRichText") {
				const html = await this.$api(finalUrl).method("POST").param(param).call();
				if (!html.status)
					return this.$message.error(html.msg);
				this.richHtmlModal.html = html.data;
				this.richHtmlModal.isShow = true;
			} else if (rowButtonItem.type === "ListRowButtonBlob") {
				try {
					const blob = await this.$api(finalUrl).method("POST").param(param).setBlob(true).call();
					var downloadElement = document.createElement("a");
					var href = window.URL.createObjectURL(blob);
					downloadElement.href = href;
					downloadElement.download = rowButtonItem.downloadFilename;
					document.body.appendChild(downloadElement);
					downloadElement.click();
					document.body.removeChild(downloadElement);
					window.URL.revokeObjectURL(href);
				} catch (e) {
					this.$message.error("文件导出时发生了错误：" + e, 5);
				}
			}
		}
	}
};
</script>