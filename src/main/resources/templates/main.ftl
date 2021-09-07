<#import "parts/common.ftl" as c>
<@c.page>

	<div class="form-row">
		<div class="form-group col-md-6"
		<form method="get" action="/main" class="form-inline">
			<input type="text" name="filter" class="form-control" value="${filter?if_exists}" placeholder="Search by Tag">
			<button type="submit" class="btn btn-primary ml-2">Search</button>
		</form>
	</div>
	</div>

	<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
	   aria-controls="collapseExample">
		Add new message
	</a>
	<div class="collapse" id="collapseExample">
		<div class="form-group mt-3">
			<form method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input class="form-control" type="text" name="text" placeholder="Введите сообщение">
				</div>
				<div class="form-group">
					<input class="form-control" type="text" name="tag" placeholder="Тэг">
				</div>
				<div class="form-group">
					<div class="custom-file">
						<input type="file" name="file" id="customFile">
						<label class="custom-file-label" for="customFile">Choose file</label>
					</div>
				</div>
				<div class="form-group">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary ">Добавить</button>
				</div>
			</form>
		</div>
	</div>
	<div class="row row-cols-1 row-cols-md-3 g-4">
        <#list messages as message>
			<div class="col">
				<div class="card my-3 h-100">
					<div class="card-img-top">
                        <#if message.filename??>
							<img src="/img/${message.filename}">
                        </#if>
					</div>
					<div class="m-2 card-text">
						<span>${message.text}</span>
						<i>${message.tag}</i>
					</div>
					<div class="card-footer text-muted">
                        ${message.authorName}
					</div>
				</div>
			</div>
        <#else>
			NO MESSAGES
        </#list>
	</div>
</@c.page>


