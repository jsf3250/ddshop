<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
            <label>商品标题：</label>
            <input class="easyui-textbox" type="text" id="title">
            <label>商品状态：</label>
            <select id="status" class="easyui-combobox" >
                <option value="0">全部</option>
                <option value="1">正常</option>
                <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>
<table id="dg"></table>

<script>
    
    function remove() {
        var selections = $('#dg').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示', '请至少选中一条记录');
            return;
        }
        $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
            if (r) {
                var ids = [];
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                $.post(
                    //url
                    'items/batch',
                    //data
                    {'ids[]': ids},
                    //callback
                    function (data) {
                        $('#dg').datagrid('reload');
                    }
                )
                /* $.post(
                     //url:请求后台的哪个地址来进行处理，相当于url,String类型
                     'items/batch',
                     //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                     {'ids[]':ids},
                     //callback:后台处理成功的回调函数，相当于success，function类型
                     function(data){
                         $('#dg').datagrid('reload');
                     },
                     //dataType:返回的数据类型，如：json，String类型
                     'json'
                 );*/
            }
        });
    }

    function add(){
        ddshop.addTabs('新增商品','item-add');
    }
    function edit(){

    }
    function down(){
        var selections = $('#dg').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示', '请至少选中一条记录');
            return;
        }
        $.messager.confirm('确认', '您确认想要上架商品吗？', function (r) {
            if (r) {
                var ids = [];
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                $.post(
                    //url
                    'items/soldout',
                    //data
                    {'ids[]': ids},
                    //callback
                    function (data) {
                        $('#dg').datagrid('reload');
                    }
                )
            }
        });
    }
    function up(){
        var selections = $('#dg').datagrid('getSelections');
        if (selections.length == 0) {
            $.messager.alert('提示', '请至少选中一条记录');
            return;
        }
        $.messager.confirm('确认', '您确认想要上架商品吗？', function (r) {
            if (r) {
                var ids = [];
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id);
                }
                $.post(
                    //url
                    'items/batch',
                    //data
                    {'ids[]': ids},
                    //callback
                    function (data) {
                        $('#dg').datagrid('reload');
                    }
                )
            }
        });
    }
    function searchForm(){
        $('#dg').datagrid('load',{
            title: $('#title').val(),
            status: $('#status').combobox('getValue')
        });
    }
    //添加工具栏
    var toolbar = [
        {
            iconCls: 'icon-add',
            text: '新增',
            handler: function () {
                ddshop.addTabs('新增商品','itrm-add');
            }
        }, {
            iconCls: 'icon-remove',
            text: '删除',
            handler: function () {
                var selections = $('#dg').datagrid('getSelections');
                if (selections.length == 0) {
                    $.messager.alert('提示', '请至少选中一条记录');
                    return;
                }
                $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                    if (r) {
                        var ids = [];
                        for (var i = 0; i < selections.length; i++) {
                            ids.push(selections[i].id);
                        }
                        $.post(
                            //url
                            'items/batch',
                            //data
                            {'ids[]': ids},
                            //callback
                            function (data) {
                                $('#dg').datagrid('reload');
                            }
                        )
                        /* $.post(
                             //url:请求后台的哪个地址来进行处理，相当于url,String类型
                             'items/batch',
                             //data:从前台提交哪些数据给后台处理，相当于data，Object类型
                             {'ids[]':ids},
                             //callback:后台处理成功的回调函数，相当于success，function类型
                             function(data){
                                 $('#dg').datagrid('reload');
                             },
                             //dataType:返回的数据类型，如：json，String类型
                             'json'
                         );*/
                    }
                });


            }
        }, {
            iconCls: 'icon-edit',
            text: '编辑',
            handler: function () {
                alert('编辑')
            }
        }, {
            iconCls: 'icon-up',
            text: '上架',
            handler: function () {
                var selections = $('#dg').datagrid('getSelections');
                if (selections.length == 0) {
                    $.messager.alert('提示', '请至少选中一条记录');
                    return;
                }
                $.messager.confirm('确认', '您确认想要上架商品吗？', function (r) {
                    if (r) {
                        var ids = [];
                        for (var i = 0; i < selections.length; i++) {
                            ids.push(selections[i].id);
                        }
                        $.post(
                            //url
                            'items/batch',
                            //data
                            {'ids[]': ids},
                            //callback
                            function (data) {
                                $('#dg').datagrid('reload');
                            }
                        )
                    }
                });
            }
        }, {
            iconCls: 'icon-down',
            text: '下架',
            handler: function () {
                var selections = $('#dg').datagrid('getSelections');
                if (selections.length == 0) {
                    $.messager.alert('提示', '请至少选中一条记录');
                    return;
                }
                $.messager.confirm('确认', '您确认想要上架商品吗？', function (r) {
                    if (r) {
                        var ids = [];
                        for (var i = 0; i < selections.length; i++) {
                            ids.push(selections[i].id);
                        }
                        $.post(
                            //url
                            'items/soldout',
                            //data
                            {'ids[]': ids},
                            //callback
                            function (data) {
                                $('#dg').datagrid('reload');
                            }
                        )
                    }
                });
            }
        }
    ];

    $('#dg').datagrid({
        toolbar:'#toolbar',
        url: 'items',
        striped: true,//隔行换色
        pagination: true,//分页工具栏
        fit: true,
        rownumbers: true,//行号
        multiSort:true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'id', title: '商品编号', width: 100},
            {field: 'title', title: '名称', width: 100,sortable:true,multiSort:true},
            {field: 'sellPoint', title: '买点', width: 100},
            {
                field: 'status', title: '状态', width: 100, formatter: function (value, rows, index) {
                switch (value) {
                    case 1:
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }
            },

            {field: 'catName', title: '分类名称', width: 100},
            {field: 'price', title: '价格', width: 100,sortable:true,multiSort:true},
            {
                field: 'created', title: '创建时间', width: 100, formatter: function (value, rows, index) {

                return moment(value).format('LL');
            }
            },
            {
                field: 'updated', title: '修改时间', formatter: function (value, rows, index) {

                return moment(value).format('LL');
            }
            }
        ]]
    });
</script>