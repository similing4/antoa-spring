<template>
	<a-form-item :label="column.tip" :label-col="{span: 7}" :wrapper-col="{span: 10}">
		<div v-for="(fileItem,index) in parse(value)" :key="index">
			<img :src="g(fileItem)" style="width: 200px" />
			<a-button type="danger" @click="onChange(parse(value).filter((t)=>{return t != fileItem;}))">删除</a-button>
		</div>
		<upload-button @uploadfinished="onChange(parse(value).concat($event.map((t)=>{return t.response;})))" accept="*/*" :multiple="true" :type="type" :col="column.col" :path="gridApiObject.api_upload"></upload-button>
		<slot />
	</a-form-item>
</template>
<script>
import UploadButton from "@/components/tool/UploadButtonLocal.vue"
export default {
	props: {
		column: {
			type: Object,
			default () {
				return {
					"col": "id",
					"tip": "",
					"default": "",
					"type": "CreateColumnPictures",
					"enum": [] // {title:"",value:"",disabled:false}
				};
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
					save: "",
					api_upload: ""
				};
			},
		},
		value: {
			type: [String, Number]
		},
        type: {
            type: String,
            default: "create"
        },
        index: {
            type: [Number, String],
            default: 0
        }
	},
	data() {
		return {};
	},
	components: {
		UploadButton
	},
	methods: {
		parse(value) {
			try {
				return JSON.parse(value);
			} catch (e) {
				return [];
			}
		},
		onChange(e) {
			this.$emit("input", JSON.stringify(e));
		},
		g(url){
			return process.env.VUE_APP_API_BASE_URL + url
		}
	}
}
</script>
