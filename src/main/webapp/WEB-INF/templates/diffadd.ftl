<#include "main-template.ftl"/>

<#macro m_body>

<div class="col-lg-6 col-lg-offset-3">
    <h1>Add new profile name</h1>
    <form action="/profiles/add" method="get">
        <div class="form-group">
            <label for="name">Name</label>
            <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
            <errors path="name"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <hr>
<h1>Manually changed diffs</h1>
<form action="/profiles/diff/man/add" method="get">
        <div class="form-group">
            <label for="username">Name</label>
            <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
            <errors path="name"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input name="description" id="description" cssClass="form-control" placeholder="description"/>
            <errors path="description"/>
        </div>
        <div class="form-group">
            <label for="action">Action</label>
            <input name="action" id="action" cssClass="form-control" placeholder="Action"/>
            <errors path="action"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
<hr>
    <h1>Locally changed diffs</h1>
<form action="/profiles/diff/loc/add" method="get">
    <div class="form-group">
        <label for="username">Name</label>
        <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
        <errors path="name"/>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <input name="description" id="description" cssClass="form-control" placeholder="description"/>
        <errors path="description"/>
    </div>
    <div class="form-group">
        <label for="action">Action</label>
        <input name="action" id="action" cssClass="form-control" placeholder="Action"/>
        <errors path="action"/>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
<hr>
    <h1>Remotely changed diffs</h1>
<form action="/profiles/diff/rem/add" method="get">
    <div class="form-group">
        <label for="username">Name</label>
        <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
        <errors path="name"/>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <input name="description" id="description" cssClass="form-control" placeholder="description"/>
        <errors path="description"/>
    </div>
    <div class="form-group">
        <label for="action">Action</label>
        <input name="action" id="action" cssClass="form-control" placeholder="Action"/>
        <errors path="action"/>
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
</form>

    <h1>Before and after metric</h1>
    <form action="/profiles/metr/aft/add" method="get">
        <div class="form-group">
            <label for="username">Name</label>
            <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
            <errors path="name"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input name="description" id="description" cssClass="form-control" placeholder="description"/>
            <errors path="description"/>
        </div>
        <div class="form-group">
            <label for="beforeaction">Before Action</label>
            <input name="beforeaction" id="beforeaction" cssClass="form-control" placeholder="Before Action"/>
            <errors path="beforeaction"/>
        </div>
        <div class="form-group">
            <label for="anotheraction">After Action</label>
            <input name="anotheraction" id="anotheraction" cssClass="form-control" placeholder="After Action"/>
            <errors path="anotheraction"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>


    <h1>Before and stream metric</h1>
    <form action="/profiles/metr/str/add" method="get">
        <div class="form-group">
            <label for="username">Name</label>
            <input name="name" id="name" cssClass="form-control" placeholder="Diff name"/>
            <errors path="name"/>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input name="description" id="description" cssClass="form-control" placeholder="description"/>
            <errors path="description"/>
        </div>
        <div class="form-group">
            <label for="beforeaction">Before Action</label>
            <input name="beforeaction" id="beforeaction" cssClass="form-control" placeholder="Before Action"/>
            <errors path="beforeaction"/>
        </div>
        <div class="form-group">
            <label for="anotheraction">Stream Action</label>
            <input name="anotheraction" id="anotheraction" cssClass="form-control" placeholder="Stream Action"/>
            <errors path="anotheraction"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>




</#macro>


<@main title="Profiles list" customScripts=["resources/js/tweet.js"]/>
