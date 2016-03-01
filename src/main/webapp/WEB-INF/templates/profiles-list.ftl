<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">
    <#list profiles as profile>
<p>${profile.name}</p>
    <form id="${profile.id}" method="get" action="/profiles/${profile.name}/add">
        <select  multiple="multiple" name="diffs">
        <#list profilediffsall as profilediff>
         <option value="${profilediff.name}"  ${profile.profileDiffs?seq_contains(profilediff)?string("selected", "")}>${profilediff.name}</option>
        </#list>
        </select>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
<hr>
</#list>


</#macro>

<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>