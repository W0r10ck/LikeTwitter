<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

	<div class="mb-1">Add new user</div>
    ${message?if_exists}

    <@l.login path="/registration" value="Add user" isRegisterForm=true />

</@c.page>

