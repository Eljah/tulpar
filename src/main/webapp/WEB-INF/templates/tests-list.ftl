<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">

    <h1>Add a new test from existing profile</h1>
    <form action="/tests/add" method="get">
        <div class="form-group">
            <label for="name">Name</label>
            <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
            <errors path="name"/>
        </div>
        <div class="form-group">
            <label for="endaction">Run Duration</label>
            <input name="runduration" id="endaction" cssClass="form-control" placeholder="End Action"/>
            <errors path="endaction"/>
        </div>
        <div class="form-group">
            <label for="endaction">Number Of Runs</label>
            <input name="numberofruns" id="endaction" cssClass="form-control" placeholder="End Action"/>
            <errors path="endaction"/>
        </div>

            <select name="profilename">
                <#list profiles as profile>
                    <option value="${profile.name}">${profile.name} (<#list profile.profileDiffs as profilediff>
                    ${profilediff.name+((profilediff.type=="2")?string("*", ""))+((profilediff.type=="3")?string("**", ""))}
                    </#list>//<#list profile.metrics as metric>
                    ${metric.name+((metric.type=="1")?string("*", ""))}
                    </#list>)</option>
                </#list>
            </select>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

<hr>
    <#list tests as test>
        <p>Test id: ${test.id}</p>
        <p>Profile Name: ${test.profile.name} (${test.profile.startAction}/${test.profile.endAction})</p>
        <p>Profile Features: <#list test.profile.profileDiffs as profilediff>
        ${profilediff.name+((profilediff.type=="2")?string("*", ""))+((profilediff.type=="3")?string("**", ""))}
        </#list>//<#list test.profile.metrics as metric>
        ${metric.name+((metric.type=="1")?string("*", ""))}
        </#list></p>
        <p>Planned:  ${(test.planned)?string("yes", "no")}</p>
        <p>Executed: ${(test.executed)?string("yes", "no")}</p>
        <form id="${test.id}_pull" method="get" action="/tests/${test.id}/pull">
            <#if !test.planned><button type="submit" class="btn btn-default">Pull</button></#if>
            <#if test.executed>Executed;</#if>
             </form>
         <p><#if test.testRuns??><#list test.testRuns as testrun><#list testrun.testRunMetricResults as testRunMetricResult>

            <#if testRunMetricResult.metric.type=="1">${testRunMetricResult.metric.name}: value: ${testRunMetricResult.average} <#if testRunMetricResult.dispersion??>(${testRunMetricResult.dispersion})</#if>; delta (less-sense): ${testRunMetricResult.averageDelta} <#if testRunMetricResult.dispersion??>(${testRunMetricResult.dispersionDelta})</#if><#if testRunMetricResult.max??>; Max: ${testRunMetricResult.max.value}; Min: ${testRunMetricResult.min.value}</#if></#if>
            <#if testRunMetricResult.metric.type=="0">${testRunMetricResult.metric.name}: value: ${testRunMetricResult.averageDelta} <#if testRunMetricResult.dispersion??>(${testRunMetricResult.dispersionDelta})</#if>; overall sum (less-sence): ${testRunMetricResult.average} <#if testRunMetricResult.dispersion??>(${testRunMetricResult.dispersion})</#if><#if testRunMetricResult.max??>; Max: ${testRunMetricResult.max.value}; Min: ${testRunMetricResult.min.value}</#if></#if>
            <br></#list></#list></#if></p>
        <p><#if test.testMetricResults??><#list test.testMetricResults as testMetricResult>
            <#if testMetricResult.metric.type=="1">Test result per metric ${testMetricResult.metric.name}: value: ${testMetricResult.average} <#if testMetricResult.dispersion??>(${testMetricResult.dispersion})</#if>; delta (less-sence): ${testMetricResult.averageDelta} (${testMetricResult.dispersionDelta})<#if testMetricResult.max??>; Max: ${testMetricResult.max.value}; Min: ${testMetricResult.min.value}</#if></#if>
            <#if testMetricResult.metric.type=="0">Test result per metric ${testMetricResult.metric.name}: value: ${testMetricResult.averageDelta} <#if testMetricResult.dispersion??>(${testMetricResult.dispersionDelta})</#if>; overall sum (less sense): ${testMetricResult.average} (${testMetricResult.dispersion})<#if testMetricResult.max??>; Max: ${testMetricResult.max.value}; Min: ${testMetricResult.min.value}</#if></#if>
            <br></#list></#if></p>
        <p><#if test.profile.profileMetricResult??><#list test.profile.profileMetricResult as profileMetricResult>Profile result per metric ${profileMetricResult.metric.name}: value: ${profileMetricResult.average} (${profileMetricResult.dispersion}); delta: ${profileMetricResult.averageDelta} (${profileMetricResult.dispersionDelta})<#if profileMetricResult.max??>; Max: ${profileMetricResult.max.value}; Min: ${profileMetricResult.min.value}</#if><#if profileMetricResult.dispersion??></#if><br></#list></#if></p>


        <form id="${test.id}_clone" method="get" action="/tests/${test.id}/clone">
            <button type="submit" class="btn btn-default">Clone</button>
        </form>
        <hr>
    </#list>
</#macro>

<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>