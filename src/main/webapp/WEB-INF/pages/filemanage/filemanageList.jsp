<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<html>
<script src="/jslib/ztree/js/jquery-1.4.4.min.js"></script>
<script src="/jslib/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<link href="/jslib/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<script>
    var setting = {
        async: {
            enable: true,
            contentType: "application/x-www-form-urlencoded",
            dataType: "text",
            url: "fileManage/queryFileAsList.json?rnd="+new Date().getTime(),
            type: "post",
            autoParam: ["filepath"]
        },
        data:{
            key:{
                name:"filename"
            },
            keep: {
                parent: true
            }
        },
        callback:{
            beforeExpand : function(treeId, treeNode){
                filetree.reAsyncChildNodes(treeNode, "refresh");
            },
            onRightClick: function(event, treeId, treeNode){
                if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
                    filetree.cancelSelectedNode();
                    showRMenu("root", event.clientX, event.clientY);
                } else if (treeNode && !treeNode.noR) {
                    filetree.selectNode(treeNode);
                    showRMenu("node", event.clientX, event.clientY);
                }
            }
        }
    };

    function showRMenu(type, x, y) {
        $("#rMenu ul").show();
        if (type=="root") {
            $("#m_add").hide();
            $("#m_del").hide();
        } else {
            $("#m_add").show();
            $("#m_del").show();
        }
        rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

        $("body").bind("mousedown", onBodyMouseDown);
    }
    function hideRMenu() {
        if (rMenu) rMenu.css({"visibility": "hidden"});
        $("body").unbind("mousedown", onBodyMouseDown);
    }
    function onBodyMouseDown(event){
        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
            rMenu.css({"visibility" : "hidden"});
        }
    }
    var addCount = 1;
    function addTreeNode() {
        hideRMenu();
        var newNode = { filename:"增加" + (addCount++)};
        if (filetree.getSelectedNodes()[0] && filetree.getSelectedNodes()[0].isParent == true) {
            newNode.checked = filetree.getSelectedNodes()[0].checked;
            filetree.addNodes(filetree.getSelectedNodes()[0], newNode);
        } else {
            filetree.addNodes(null, newNode);
        }
    }
    function removeTreeNode() {
        hideRMenu();
        var nodes = filetree.getSelectedNodes();
        if (nodes && nodes.length>0) {
            if (nodes[0].isParent == true) {
                var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
                if (confirm(msg)==true){
                    filetree.removeNode(nodes[0]);
                }
            } else {
                filetree.removeNode(nodes[0]);
            }
        }
    }
    function resetTree() {
        hideRMenu();
        $.fn.zTree.init($("#filetree"), setting);
    }
    $(document).ready(function () {
        $.fn.zTree.init($("#filetree"), setting);
        filetree = $.fn.zTree.getZTreeObj("filetree");
        rMenu = $("#rMenu");

    });
</script>
<style type="text/css">
    div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
    div#rMenu ul li{
        margin: 1px 0;
        padding: 0 5px;
        cursor: pointer;
        list-style: none outside none;
        background-color: #DFDFDF;
    }

    div, ul, li{
        margin: 0;padding: 0;border: 0;outline: 0;font-weight: inherit;font-style: inherit;font-size: 100%;font-family: inherit;vertical-align: baseline;
    }

    ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:350px;height:550px;overflow-y:scroll;overflow-x:auto;}
    ul.log {border: 1px solid #617775;background: #f0f6e4;width:300px;height:170px;overflow: hidden;}
    ul.log.small {height:45px;}
    ul.log li {color: #666666;list-style: none;padding-left: 10px;}
    ul.log li.dark {background-color: #E3E3E3;}
</style>
<body>
<div>
    <ul id="filetree" class="ztree"></ul>
</div>
<div id="rMenu">
    <ul>
        <li id="m_add" onclick="addTreeNode();">增加节点</li>
        <li id="m_del" onclick="removeTreeNode();">删除节点</li>
        <li id="m_reset" onclick="resetTree();">恢复</li>
    </ul>
</div>
</body>
</html>