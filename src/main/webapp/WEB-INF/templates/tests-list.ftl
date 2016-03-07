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
        <p><#if test.testRuns??><#list test.testRuns as testrun><#list testrun.testRunMetricResults as testRunMetricResult>Metric: ${testRunMetricResult.metric.name}: Average: ${testRunMetricResult.average}; Average Delta: ${testRunMetricResult.averageDelta}<#if testRunMetricResult.max??>; Max: ${testRunMetricResult.max.value}; Min: ${testRunMetricResult.min.value}</#if><#if testRunMetricResult.dispersion??>; Dispersion: ${testRunMetricResult.dispersion}</#if><br></#list></#list></#if></p>
        <p><#if test.testMetricResults??><#list test.testMetricResults as testMetricResult>Test result per metric: ${testMetricResult.metric.name}: Average: ${testMetricResult.average}; Average Delta: ${testMetricResult.averageDelta}<#if testMetricResult.max??>; Max: ${testMetricResult.max.value}; Min: ${testMetricResult.min.value}</#if><#if testMetricResult.dispersion??>; Dispersion: ${testMetricResult.dispersion}</#if><br></#list></#if></p>
        <p><#if test.profile.profileMetricResult??><#list test.profile.profileMetricResult as profileMetricResult>Profile result per metric: ${profileMetricResult.metric.name}: Average: ${profileMetricResult.average}; Average Delta: ${profileMetricResult.averageDelta}<#if profileMetricResult.max??>; Max: ${profileMetricResult.max.value}; Min: ${profileMetricResult.min.value}</#if><#if profileMetricResult.dispersion??>; Dispersion: ${profileMetricResult.dispersion}</#if><br></#list></#if></p>
        <form id="${test.id}_clone" method="get" action="/tests/${test.id}/clone">
            <button type="submit" class="btn btn-default">Clone</button>
        </form>
        <hr>
    </#list>
</#macro>

<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>