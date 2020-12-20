<#include "/inc/layout.ftl" />

<@layout "用户中心">
  <div class="layui-container fly-marginTop fly-user-main">
    <@centerLeft level=1></@centerLeft>

    <div class="site-tree-mobile layui-hide">
      <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>

    <div class="site-tree-mobile layui-hide">
      <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>


    <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title" id="LAY_mine">
          <li lay-id="index" class="layui-this">我发的帖（<span id="fabu-total"></span>）</li>
          <li lay-id="collection">我收藏的帖（<span id="collection-total"></span>）</li>
        </ul>
        <div class="layui-tab-content" style="padding: 20px 0;">

          <div class="layui-tab-item layui-show">
            <ul class="mine-view jie-row" id="fabu">
              <#--我发的贴模板-->
              <script id="tpl-fabu" type="text/html">
                <li>
                  <a class="jie-title" href="/post/{{ d.id }}" target="_blank">{{ d.title }}</a>
                  <i>{{layui.util.toDateString(d.created, 'yyyy-MM-dd HH:mm:ss')}}</i>
                  <a class="mine-edit" href="/post/edit?id={{ d.id }}">编辑</a>
                  <em>{{d.viewCount}}阅/{{d.commentCount}}答</em>
                </li>
              </script>
            </ul>
            <div id="LAY_page"></div>
          </div>

          <div class="layui-tab-item">
            <ul class="mine-view jie-row" id="shoucang">
              <#--我收藏的贴模板-->
              <script id="tpl-collection" type="text/html">
                <li>
                <a class="jie-title" href="/post/{{d.postId}}" target="_blank">{{d.title}}</a>
                <i>收藏于{{layui.util.timeAgo(d.created, true)}}</i>
              </li>
              </script>
            </ul>
            <div id="LAY_page1"></div>
          </div>

        </div>
      </div>
    </div>
  </div>

  <script>
    layui.cache.page = 'user';

    layui.use(['laytpl', 'flow', 'util'], function() {
      var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
      var laytpl = layui.laytpl;
      var flow = layui.flow;
      var util = layui.util;

      flow.load({
        elem: '#fabu' //指定列表容器
        ,isAuto: false //不自动加载,会生成一个加载更多的按钮
        ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
          var lis = []; //存放加载内容的容器
          //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
          $.get('/user/public?pn='+page, function(res){
            $("#fabu-total").text(res.data.total);
            layui.each(res.data.records, function(index, item){
              var tpl = $("#tpl-fabu").html();
              laytpl(tpl).render(item, function (html) {
                  $("#fabu .layui-flow-more").before(html);
              });
            });

            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
            next(lis.join(''), page < res.data.pages);
          });
        }
      });

      flow.load({
        elem: '#shoucang'
        ,isAuto: false
        ,done: function(page, next){
          var lis = [];

          $.get('/user/collection?pn='+page, function(res){
            $("#collection-total").text(res.data.total);
            layui.each(res.data.records, function(index, item){
              var tpl = $("#tpl-collection").html();
              laytpl(tpl).render(item, function (html) {
                $("#shoucang .layui-flow-more").before(html);
              });
            });

            next(lis.join(''), page < res.data.pages);
          });
        }
      });
    });

  </script>
</@layout>