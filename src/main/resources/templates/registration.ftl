<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

	Add new user

	<#if message??>
        ${message}
	</#if>


    <@l.login path="/registration" value="Add user"/>

</@c.page>

