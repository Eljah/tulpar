<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">
    <#list profilediffs as profilediff>
        <p>id: ${profilediff.id}</p>
        <p>Name: ${profilediff.name}</p>
        <p>Description: ${profilediff.description}</p>
        <p>Action: ${profilediff.action}</p>
        <p>Type: ${profilediff.type}</p>
        <hr>
    </#list>
</#macro>

<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>