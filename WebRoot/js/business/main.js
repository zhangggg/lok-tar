var tree1setting = {
	callback:{
		onClick:page1TreeClick
	},
	data: {
		key:{
			children:"sons",
			name:"zj"
		},
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "fatherZJ",
			rootPId: 0
		}
	}
};

var tree2setting = {
	callback:{
		onClick:page2TreeClick
	},
	data: {
		key:{
			children:"sons",
			name:"shortKhnr",
			title:"khnr"
		},
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "khzb"
		}
	}
};

$(document).ready(function(){
	tree1();
	tree2();
	//$.fn.zTree.init($("#tree1"), setting, zNodes);
});
/**
 * 第一颗树
 */
function tree1() {
	var keyword = $("#page1text").val();
	$.ajax({
		type : "POST",
		url : "zlml/tree",
		data : {"keyword":keyword},
		dataType : "json",
		success : function(data) {
			$.fn.zTree.init($("#tree1"), tree1setting, data);
		}
     });
}
function tree2(){
	var fl = $("#fl_select").val();
	$.ajax({
		type : "POST",
		url : "khzb/tree",
		data : {"fl":fl},
		dataType : "json",
		success : function(data) {
			$.fn.zTree.init($("#tree2"), tree2setting, data);
		}
     });
	loadTask();
}


function page1TreeClick(event, treeId, treeNode) {
	$.ajax({
		type : "POST",
		url : "zlml/byId",
		data : {"ID":treeNode.id},
		dataType : "json",
		success : function(data) {
			$("#page1TextArea").val(data.nr);
		}
     });
};
function page2TreeClick(event, treeId, treeNode){
	$.ajax({
		type : "POST",
		url : "khzb/byId",
		data : {"ID":treeNode.id},
		dataType : "json",
		success : function(data) {
			$("#khnr").val(data.khnr);
			$("#kfbz").val(data.kfbz);
			$("#yszynr").val(data.yszynr);
			$("#zcqk").val(data.zcqk);
			$("#page2score").val(data.fs);
			$("#mytable tr").empty();
			if(data.khzb != 0){
				$("#currentID").val(data.id);
				loadTable(data.id);
			}else{
				$("#currentID").val(0);
			}
		}
     });
}
 
function loadTable(khzbid){
	$.ajax({
		type : "POST",
		url : "lj/byId",
		data : {"khzbid":khzbid},
		dataType : "json",
		success : function(data) {
			var html ="";
			for(var i=0;i<data.length;i++){
				html += "<tr>"
				html += "<td class=\"fixedTd\"><a href=\""+data[i].path+"\" target=\"_blank\">" + data[i].context + "</a></td>" 
				html += "<td class=\"fixedTd\">" + data[i].username + "</td>"
				html += "<td ><div class=\"btnarea\">" +
						"<input name=\"upload\" type=\"button\" class=\"btn_upload\" onclick=\"javascript:openUploadPage("+data[i].ID+")\" />";
				if(data[i].app != null && data[i].app != ''){
					html += "<a class=\"btn_download\" href=\"lj/download?id="+ data[i].ID +"\"/>";
				}
				html += "</div></td>"
				html += "</tr>"
		    }
			$("#mytable").html(html);
		}
     });
}

function loadTask(){
	var code = $("#fl_select").val();
	if(code == null || code == ''){
		$('#groupTaskArea').val("");
	}else{
		$.ajax({
			type : "POST",
			url : "dict/dt",
			data : {"code":code},
			dataType : "json",
			success : function(data) {
				$('#groupTaskArea').val(data.val);
			}
	     });
	}
}

function sumbitTask(){
	var code = $("#fl_select").val();
	var values = $("#groupTaskArea").val();
	if(code == null || code == ''){
		$('#groupTaskArea').val("");
	}else{
		$.ajax({
			type : "POST",
			url : "dict/uptByCode",
			data : {"code":code,"values":values},
			dataType : "json",
			success : function(data) {
				alert("任务发布成功")
			}
	     });
	}
}

function openUploadPage(id){
	window.open('fileUpload?id=' + id,'_fileUpload','','width=200,height=100')
	
}

function save(){
	var id = $("#currentID").val();
	var zcqk = $("#zcqk").val();
	
	if (id != null && id != 0){
		$.ajax({
			type : "POST",
			url : "khzb/update",
			data : {"ID":id,"zcqk":zcqk},
			dataType : "json",
			success : function(data) {
				alert("自查保存成功")
			}
	     });
	}
}

function marking(){
	var id = $("#currentID").val();
	var fs = $("#page2score").val();
	
	if (id != null && id != 0){
		$.ajax({
			type : "POST",
			url : "khzb/marking",
			data : {"ID":id,"fs":fs},
			dataType : "json",
			success : function(data) {
				alert("打分保存成功")
			}
	     });
	}
}

function getScores(){
	var fl = $("#fl_select").val();
	$.ajax({
		type : "POST",
		url : "khzb/getScores",
		data : {"fl":fl},
		dataType : "json",
		success : function(data) {
			alert("当前组别总分:" + data.count);
		}
     });
}