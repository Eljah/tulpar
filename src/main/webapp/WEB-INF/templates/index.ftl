<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">
    <textarea id="js-tweet-text" class="form-control" rows="3" title="tweet"></textarea>
    <input type="submit" id="js-sendtweet">
</div>

<div id="js-tweets" class="col-lg-6 col-lg-offset-3">

</div>

<li>
    <a href="/profiles/diff/add">Add Profile Diffs</a>
</li>
<li>
    <a href="/profiles/getAll">All profiles</a>
</li>
<li>
    <a href="/profiles/diff/getAll">All profile diffs</a>
</li>

</#macro>

<@main title="Главная" customScripts=["resources/js/tweet.js"]/>