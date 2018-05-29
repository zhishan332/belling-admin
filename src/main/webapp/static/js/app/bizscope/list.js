/**
 * Created by Administrator on 2017/2/27 0027.
 * @desc  : 用户列表JS
 * @author: Chenjx
 * @demo http://jadmin.jsyso.com/a
 */
// 配置模块
layui.config({
	base : './../static/js/',
	version: new Date().getTime()
}).extend({ // 模块别名
	page_table : 'page_table',
	jacommon : 'jacommon'
});

// Table变量
var _tableObj, _datatable;
// 日期节点
var startTime, endTime;
// 加载所需模块
layui.use(['form','layer', 'laytpl', 'page_table', 'laydate', 'jacommon', 'tab'], function(){
	//  操作对象
	var form = layui.form(),
		layer = layui.layer , 
		laytpl = layui.laytpl,
		pageTable = layui.page_table,
		laydate = layui.laydate,
		jacommon = layui.jacommon,
		$ = layui.jquery;

	// 初始化表格
	_tableObj = $('#dataTable');
	_datatable = _tableObj.DataTable({
//		"dom": '<"top">rt<"bottom"flp><"clear">',
		"autoWidth": false,                     // 自适应宽度
		"stateSave": true,                      // 刷新后保存页数
		"bDestroy" : true,//不加这个在第二次展示此表格时报错
		"serverSide": true,
		"bRetrieve": true,
		"deferRender": true,                    // 当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
		"sServerMethod" : "POST",               // POST
		"ajax": {
			"url": WEB_ROOT + "/biz/scope/page",
			"type": "POST",
			"data" : function(d) {
				var keyword = $.trim($("#keyword").val());
				console.log('keyword:' + keyword);
				d.keyword = keyword;
				d.uId = U_ID;
				d.v = "1.0.1";
			},
			"error": handleAjaxError // this sets up jQuery to give me errors  
		},
		"pagingType": "full_numbers",         // 分页样式 simple,simple_numbers,full,full_numbers
		"displayLength": 8, // 每页显示条数
		"rowId": 'id', // row加id
		"order": [[ 1, "desc" ]],               // 排序
		"columns": [// 自定义数据列
            {"data": null, class: 'text-c'},
			{data: 'bizName'},
			{data: 'bizValue'},
			{data: 'bizAddr'},
            {data: 'bizContact'},
            {data: 'createUser'},
			{data: 'createTime'},
            {data: null}
		],
		"language" : {
			"sProcessing" : "正在获取数据，请稍后...",
			"sLengthMenu" : "",    
			"zeroRecords" : "没有找到数据",
			"info" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 ), 共 _TOTAL_ 条记录",
			"infoEmpty" : "暂无数据",
			"sLoadingRecords" : "加载中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		},
		"searching" : false, // 是否显示搜索
		"ordering" : false,
		"bInfo" : true, // 是否显示页脚分页
		"bPaginate" : true,
		"stateSaveParams": function () {           // 初始化完成调用事件
            // 重新渲染form checkbox 如果你要使用layui的复选框样式打开这个
            form.render('checkbox');
		},
		"fnRowCallback" : function(row, data, index) {
            // 多选按钮
            var opcheckboxtpl = document.getElementById('opcheckbox').innerHTML;
            // 数据菜单
            laytpl(opcheckboxtpl).render(data, function(html){
                $('td:eq(0)', row).html(html);
            });

            // 是否
            var opOntpl = document.getElementById('opOn').innerHTML;
            // 数据菜单
            laytpl(opOntpl).render(data, function(html){
                $('td:eq(2)', row).html(html);
            });

            // 操作菜单
            var opbtntpl = document.getElementById('opbtn').innerHTML;
            // 数据菜单
            laytpl(opbtntpl).render(data, function(html){
                $('td:eq(6)', row).html($.trim(html) == '' ? '--' : html);
            });

            return row;
		},
		"initComplete": function () {
			
		},
		"footerCallback": function (row, data, start, end, display ) {
			
		}
	});
	
	// 请求异常拦截
	function handleAjaxError(xhr, textStatus, error) {
		jacommon.error('用户会话已失效或已被强制下线！', function(){
			parent.location.reload();
		});
	}

    // 绑定table内操作事件
    _tableObj.on("click",".btn-edit", function() {
        //点击编辑按钮
        var _id = $(this).attr('data-id');
        var index = layer.open({
            type: 2,
            title: '编辑用户',
            content: WEB_ROOT + "/admin/user/edit?id=" + _id
        });
        layer.full(index);
    }).on("click",".btn-del", function() {
        //点击删除按钮
        var _id = $(this).attr('data-id');
        jacommon.confirm('确认删除吗，此操作不可逆？', function() {
            var postUrl = WEB_ROOT + '/admin/user/delete?t=' + new Date().getTime();
            var postData = {'ids' : _id};
            // 提交请求
            jacommon.ajaxPost(postUrl, postData, function(res) {
                jacommon.success('删除成功', function() {
                    refresh();
                });
            }, function(res) {
                jacommon.error('删除失败，' + res.message + '(错误代码：' + res.code + ')');
            }, function() {

            });
        });
    });
	
	// 列表数据操作监听
	$(document).on('click','#btn-delete-all', function(){
		var _dateObj = $("#clear-date");
		var _val = _dateObj.val();
		if(_val == null || _val == ''){
			jacommon.msgError('请选择清理的时间范围！！');
		} else {
			var startTime = laydate.now();
			if (_val != 1)  {
				startTime = laydate.now(-_val);
			}
			startTime = jacommon.parseStr2TimeStamp(startTime + " 00:00");
			//console.log('startTime' + startTime);
			var _txt = _dateObj.find("option:selected").text();
			jacommon.confirm('确认删除' + _txt + '的登录日志吗？', function() {
				var postUrl = WEB_ROOT + '/admin/loginlog/delete?t=' + new Date().getTime();
				var postData = {'startTime' : startTime};
				// 提交请求
				jacommon.ajaxPost(postUrl, postData, function(res) {
					jacommon.success('清理成功', function() {
						refresh();
					});
				}, function(res) {
					jacommon.error('清理失败，' + res.message + '(错误代码：' + res.code + ')');
				}, function() {
					 
				});
			});
		}
	}).on('click','#btn-search', function(){
		var startTime = $.trim($("#startTime").val());
		if (startTime != '') {
			startTime = jacommon.parseStr2TimeStamp(startTime);
		} else {
			startTime = null;
		}
		
		var endTime = $.trim($("#endTime").val());
		if (endTime != '') {
			endTime = jacommon.parseStr2TimeStamp(endTime);
		} else {
			endTime = null;
		}
		
		if (null != startTime && null != endTime) {
			if (startTime >= endTime) {
				jacommon.msgError('开始时间必须小于结束时间！！');
				return false;
			}
		}
		refresh();
	}).on('click','#btn-add', function(){
//		parent.tab.tabAdd({
//			href: WEB_ROOT + '/admin',
//			icon: 'fa-gear',
//			title: '设置'
//		});
        var index = layer.open({
            type: 2,
            title: '添加业务区域',
            content: WEB_ROOT + "/biz/scope/edit"
        });
        layer.full(index);
    });
	
	// 回车事件监听
	$(document).keyup(function(event){
		if(event.keyCode ==13){
			refresh();
		}
	});
});

/**
 * 刷新当前窗口
 */
function refresh() {
	_datatable.draw();
}