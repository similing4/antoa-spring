<template>
	<a-spin :spinning="!isLoadOk">
		<a-form v-if="isLoadOk">
			<template v-for="(column,index) in gridCreateObject.createColumnCollection">
				<CreateColumnCascader :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnCascader'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnCascader>
				<CreateColumnText :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnText'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnText>
				<CreateColumnChildrenChoose :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnChildrenChoose'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnChildrenChoose>
				<CreateColumnDisplay :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnDisplay'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnDisplay>
				<CreateColumnDivideNumber :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnDivideNumber'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnDivideNumber>
				<CreateColumnEnum :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnEnum'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnEnum>
				<CreateColumnEnumCheckBox :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnEnumCheckBox'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnEnumCheckBox>
				<CreateColumnEnumRadio :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnEnumRadio'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnEnumRadio>
				<CreateColumnEnumTreeCheckBox :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnEnumTreeCheckBox'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnEnumTreeCheckBox>
				<CreateColumnFile :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnFile'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnFile>
				<CreateColumnFiles :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnFiles'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnFiles>
				<CreateColumnFileLocal :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnFileLocal'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnFileLocal>
				<CreateColumnFilesLocal :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnFilesLocal'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnFilesLocal>
				<CreateColumnPassword :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnPassword'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPassword>
				<CreateColumnPicture :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnPicture'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPicture>
				<CreateColumnPictures :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnPictures'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPictures>
				<CreateColumnPictureLocal :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnPictureLocal'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPictureLocal>
				<CreateColumnPicturesLocal :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnPicturesLocal'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPicturesLocal>
				<CreateColumnRichText :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnRichText'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnRichText>
				<CreateColumnTextarea :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnTextarea'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnTextarea>
				<CreateColumnTimestamp :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type == 'CreateColumnTimestamp'" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnTimestamp>
				<CreateColumnPlugin :column="column" :grid-api-object="gridApiObject" v-model="form[column.col]" v-if="column.type.startsWith('PluginCreateColumn')" v-show="displayColumns.includes(column.col)" @input="onFormItemChange(column.col);$forceUpdate();" :type="type" :index="index">
					<RowButton v-if="getApiButtonByColumn(column.col)" :form="form" :button="getApiButtonByColumn(column.col)" @formchange="onFormChange" />
				</CreateColumnPlugin>
			</template>
		</a-form>
		<confirm-dialog ref="confirmDialog"></confirm-dialog>
	</a-spin>
</template>
<script>
import moment from "moment";
import RowButton from "../create/RowButton.vue";
import CreateColumnCascader from "../create/column_column/CreateColumnCascader.vue";
import CreateColumnChildrenChoose from "../create/column_column/CreateColumnChildrenChoose.vue";
import CreateColumnText from "../create/column_column/CreateColumnText.vue";
import CreateColumnDisplay from "../create/column_column/CreateColumnDisplay.vue";
import CreateColumnDivideNumber from "../create/column_column/CreateColumnDivideNumber.vue";
import CreateColumnEnum from "../create/column_column/CreateColumnEnum.vue";
import CreateColumnEnumCheckBox from "../create/column_column/CreateColumnEnumCheckBox.vue";
import CreateColumnEnumRadio from "../create/column_column/CreateColumnEnumRadio.vue";
import CreateColumnEnumTreeCheckBox from "../create/column_column/CreateColumnEnumTreeCheckBox.vue";
import CreateColumnFile from "../create/column_column/CreateColumnFile.vue";
import CreateColumnFiles from "../create/column_column/CreateColumnFiles.vue";
import CreateColumnFileLocal from "../create/column_column/CreateColumnFileLocal.vue";
import CreateColumnFilesLocal from "../create/column_column/CreateColumnFilesLocal.vue";
import CreateColumnPassword from "../create/column_column/CreateColumnPassword.vue";
import CreateColumnPicture from "../create/column_column/CreateColumnPicture.vue";
import CreateColumnPictures from "../create/column_column/CreateColumnPictures.vue";
import CreateColumnPictureLocal from "../create/column_column/CreateColumnPictureLocal.vue";
import CreateColumnPicturesLocal from "../create/column_column/CreateColumnPicturesLocal.vue";
import CreateColumnRichText from "../create/column_column/CreateColumnRichText.vue";
import CreateColumnTextarea from "../create/column_column/CreateColumnTextarea.vue";
import CreateColumnTimestamp from "../create/column_column/CreateColumnTimestamp.vue";
import CreateColumnPlugin from "../CreateColumnPluginGeneral.vue";
import ConfirmDialog from "@/components/tool/ConfirmDialog.vue";
export default {
	props: {
		gridApiObject: {
			type: Object,
			default () {
				return {
					api_column_change: "", //
					detail_column_list: "", //
					api_upload: "", //
				}
			}
		},
		gridCreateObject: {
			type: Object,
			default () {
				return {
					"createColumnCollection": [], //{"col": "id","tip": "","default": "","type": "CreateColumnHidden"}
					"createRowButtonBaseCollection": [], //["bindCol":"","apiUrl":"","buttonText":"","buttonType":""]
					"createOrEditColumnChangeHookCollection": null
				};
			}
		},
		index: {
			type: [Number, String],
			default: 0
		},
		type: {
			type: String,
			default: "easy_row"
		},
		rowData: {
			type: Object,
			default () {
				return {};
			}
		}
	},
	data() {
		return {
			isLoadOk: false,
			displayColumns: [],
			form: {},
		};
	},
	components: {
		CreateColumnCascader,
		CreateColumnChildrenChoose,
		CreateColumnDisplay,
		CreateColumnDivideNumber,
		CreateColumnText,
		CreateColumnEnum,
		CreateColumnEnumCheckBox,
		CreateColumnEnumRadio,
		CreateColumnEnumTreeCheckBox,
		CreateColumnFile,
		CreateColumnFiles,
		CreateColumnFileLocal,
		CreateColumnFilesLocal,
		CreateColumnPassword,
		CreateColumnPicture,
		CreateColumnPictures,
		CreateColumnPictureLocal,
		CreateColumnPicturesLocal,
		CreateColumnRichText,
		CreateColumnTextarea,
		CreateColumnTimestamp,
		CreateColumnPlugin,
		ConfirmDialog,
		RowButton
	},
	async mounted() {
		try {
			this.gridCreateObject.createColumnCollection.map((createColumnItem) => {
				this.form[createColumnItem.col] = "";
				this.displayColumns.push(createColumnItem.col);
			});
			await this.reset();
			this.isLoadOk = true;
		} catch (e) {
			this.$message.error("配置加载错误：" + e, 5);
		}
	},
	methods: {
		getApiButtonByColumn(column) {
			let ret = this.gridCreateObject.createRowButtonBaseCollection.filter((t) => t.bindCol == column.col);
			if (ret.length == 0)
				return null;
			return ret[0];
		},
		onFormItemChange(col1) {
			this.gridCreateObject.createOrEditColumnChangeHookCollection.filter((col) => {
				return col1 === col.col;
			}).map((t) => {
				this.onHookCall(t.col);
			});
		},
		async onHookCall(hookCol) {
			try {
				let res = await this.$api(this.gridApiObject.api_column_change).method("POST").param({
					type: this.type,
					index: this.index,
					form: this.form,
					page: {
						query: this.$route.query,
						record: this.rowData
					},
					col: hookCol
				}).call();
				if (res.status)
					this.onFormChange(res);
				else
					throw res.msg;
			} catch (e) {
				this.$message.error(e + "", 5);
			}
		},
		onFormChange(res) {
			Object.assign(this.form, res.data);
			if (res.displayColumns && res.displayColumns.length > 0)
				this.displayColumns = res.displayColumns;
		},
		async reset() {
			try {
				for (let i in this.gridCreateObject.createColumnCollection)
					this.form[this.gridCreateObject.createColumnCollection[i].col] = (this.gridCreateObject.createColumnCollection[i].default ? this.gridCreateObject.createColumnCollection[i].default : "");
				for (let i = 0; i < this.gridCreateObject.createOrEditColumnChangeHookCollection.length; i++)
					await this.onHookCall(this.gridCreateObject.createOrEditColumnChangeHookCollection[i].col);
				this.$forceUpdate();
			} catch (e) {
				this.$message.error(e + "", 5);
			}
		},
		async submit(callback) {
			const param = {};
			this.gridCreateObject.createColumnCollection.map((col) => {
				if (col.type === 'CreateColumnDisplay')
					return;
				param[col.col] = this.form[col.col];
			});
			callback(param);
		}
	}
};
</script>
<style scoped>
.antoa-list-filter-item {
	padding-bottom: 20px;
}

.antoa-list-operator {
	padding-bottom: 20px;
}

.antoa-list-filter-label {
	display: flex;
	flex-direction: row;
	justify-content: flex-end;
	align-items: center;
	font-weight: 400;
	height: 32px;
	padding-right: 12px;
}
</style>