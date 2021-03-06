<#macro login path value isRegisterForm>
	<form action="${path}" method="post">
		<div class="form-group row">
			<label class="rol-sm-2 col-form-label"> User Name :</label>
			<div class="col-auto">
				<input type="text" name="username" class="form-control" placeholder="User name" />
			</div>
		</div>
		<div class="form-group row">
			<label class="rol-sm-2 col-form-label"> Password:</label>
			<div class="col-auto">
				<input type="password" name="password" class="form-control" placeholder="Password" />
			</div>
		</div>
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
		<button class="btn btn-primary" type="submit">${value}</button>
	</form>
</#macro>

<#macro logout>
	<form action="/logout" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button class="btn btn-primary" type="submit" >Sign Out</button>
	</form>
</#macro>