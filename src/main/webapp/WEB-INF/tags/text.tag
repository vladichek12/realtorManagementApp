<%@ attribute name="headerr" required="true" %>
<%@ attribute name="content" required="true" %>
<%@ attribute name="text1" required="true" %>
<%@ attribute name="text2" required="true" %>
<%@ attribute name="text3" required="true" %>
<div>
    <h1 style="text-align: center" class="text-for-index-header">
        ${headerr}
    </h1>
    <h2 class="text-for-index-header">
        ${content}
    </h2>
    <h3 class="text-for-index" style="padding-right: 210px">
        ${text1}
    </h3>
    <h2 class="text-for-index-header">
        ${text2}
    </h2>
    <h3 class="text-for-index" style="padding-right: 210px">
        ${text3}
    </h3>
</div>