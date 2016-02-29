<#list tweets as tweet>
<p>${tweet.text}</p>
<p>${tweet.createdAt} by ${tweet.user.username}</p>
<hr>
</#list>