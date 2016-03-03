<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">
    <#list profiles as profile>
        <p>${profile.name} (${profile.startAction}/${profile.endAction})
           <hr/>
            (<#list profile.profileDiffs as profilediff>
            ${profilediff.name+((profilediff.type=="2")?string("*", ""))+((profilediff.type=="3")?string("**", ""))}
            </#list>
            /
            <#list profile.metrics as metric>
            ${metric.name+((metric.type=="1")?string("*", ""))}
            </#list>
            )</p>

        <hr>

        <table>
            <tr>
                <td>
                    <form id="${profile.id}" method="get" action="/profiles/${profile.name}/addprofile">
                        <select multiple="multiple" name="diffs">
                            <#list profilediffsall as profilediff>
                                <option value="${profilediff.name}"  ${profile.profileDiffs?seq_contains(profilediff)?string("selected", "")}>${profilediff.name} ${(profilediff.type=="2")?string("*", "")} ${(profilediff.type=="3")?string("**", "")}</option>
                            </#list>
                        </select>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </td>
                <td>
                    <form id="${profile.name}_metric" method="get" action="/profiles/${profile.name}/addmetric">
                        <select multiple="multiple" name="diffs">
                            <#list metrics as metric>
                                <option value="${metric.name}"  ${profile.metrics?seq_contains(metric)?string("selected", "")}>${metric.name} ${(metric.type=="1")?string("*", "")}</option>
                            </#list>
                        </select>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </td>
            </tr>
        </table>
        <form id="${profile.name}_clone" method="get" action="/profiles/${profile.name}/clone">
            <button type="submit" class="btn btn-default">Clone</button>
        </form>


    </#list>


</#macro>

<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>