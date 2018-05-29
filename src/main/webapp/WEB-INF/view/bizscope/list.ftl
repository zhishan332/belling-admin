<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Data-Table 表格</title>
	<#include "/widget/common-css.html">
</head>
<body class="body">
<fieldset class="layui-elem-field layui-field-title">
    <legend>
        业务区域列表
    </legend>
</fieldset>
<#-- 操作按钮  -->
<div class="my-btn-box">
		<span class="fl">
			<div class="layui-btn-group">
				<a class="layui-btn layui-btn-primary radius" id="btn-delete-all">
					 <i class="layui-icon">&#xe640;</i> 删除
				</a>
				<a class="layui-btn layui-btn-primary" id="btn-add">
					 <i class="layui-icon">&#xe654;</i> 添加
				</a>
            </div>
		</span>
    <span class="fr">
			<span class="layui-form-label none">搜索条件：</span>
			<div class="layui-input-inline">
				<input type="text" autocomplete="off" placeholder="名称、编号、地址等" id="keyword" class="layui-input">
			</div>
			<button class="layui-btn mgl-20" id="btn-search">
				<i class="layui-icon">&#xe615;</i>&nbsp;查 询
			</button>
		</span>
</div>

<#-- Table  -->
<table id="dataTable" class="layui-table">
    <thead>
    <tr>
        <th width="25">
            <input type="checkbox" class="my-checkbox"/>
        </th>
        <th width="100">区域名称</th>
        <th width="50">区域编号</th>
        <th width="100">地址</th>
        <th width="100">联系方式</th>
        <th width="100">创建人</th>
        <th width="100">创建时间</th>
        <th width="100">操作</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script type="text/javascript">
    <!--
    var U_ID = null;
    <@shiro.lacksRole name="admin">
        U_ID = '${user.account}';
    </@shiro.lacksRole>
    //-->
</script>
<#-- 操作按钮   -->
<script id="opbtn" type="text/html">
    <@shiro.hasPermission name="sys:user:edit">
		<a class="layui-btn layui-btn-small  layui-btn-normal btn-edit" data-id="{{ d.id }}">编辑</a>
    </@shiro.hasPermission>

		<@shiro.hasPermission name="sys:user:delete">
		<a class="layui-btn layui-btn-small layui-btn-danger btn-del" data-id="{{ d.id }}">删除</a>
        </@shiro.hasPermission>
</script>
<script type="text/javascript" src="${base}/static/plugins/layui/layui.js"></script>
<#-- 包含分页插件 -->
	<#include "/widget/paging-js.ftl">
<script type="text/javascript" src="${base}/static/js/app/bizscope/list.js?2"></script>
</body>
</html>