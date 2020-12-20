<#include "/inc/layout.ftl" />

<@layout "404页面">
<#include "/inc/header-panel.ftl" />

    <div class="layui-container fly-marginTop">
        <div class="fly-panel">
            <div class="fly-none">
                <h2><i class="iconfont icon-tishilian"></i></h2>
                <p>${message}</p>
            </div>
        </div>
    </div>

    <script>
      layui.cache.page = '';
    </script>
</@layout>