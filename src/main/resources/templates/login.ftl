<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
	Login page
	<@l.login path ="/login" value="Sign In"/>
	<a href="/registration">Add new user</a>

</@c.page>


