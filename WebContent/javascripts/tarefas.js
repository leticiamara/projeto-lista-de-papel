$(function() {

	var server = "http://localhost:8080/ListaDePapel";
	var $lastClicked;
	var status

	
	//Marcar uma tarefa como concluida
	function onTarefaConcluirClick() {
		$(this).parent('.tarefa-item')
		.off('click')
		.hide('slow', function() {
					
			$this = $(this);
			$.post(server + "/ConcluirTarefa",
			{id: $this.children(".tarefa-id").text()})
			.done(function(data){
				status = data;
				console.log("Status no onTarefaConcluir", status);
				addTarefa($this.children(".tarefa-text").text(),$this.children(".tarefa-id").text(),status);
			});
			$(this).remove();
		});
		
	}
	
	//remover item quando clicar na lixeira
	function onTarefaDeleteClick() {
		$(this).parent('.tarefa-item')
		.off('click')
		.hide('slow', function() {
			
			$this = $(this);
			$.post(server + "/DeletarTarefa",
			{id: $this.children(".tarefa-id").text()});
			$(this).remove();
		});
	}

	//recebe um texto e adiciona a tarefa à lista.
	function addTarefa(text,id,status) {
		//verifica se o id nao é undefined
		id = id || 0;

		var $tarefa = $("<div />")
		.addClass("tarefa-item")
		.append($("<div />")
			.addClass("tarefa-text")
			.text(text))
		.append($("<div />")
			.addClass("tarefa-concluir"))
		.append($("<div />")
			.addClass("tarefa-delete"))
			.append($("<div />")
			.addClass("tarefa-id")
			.text(id));
		console.log("Status no addTarefa", status);
		if(status==="done"){
			$("#tarefa-concluida-list").append($tarefa);
		}else{
			$("#tarefa-list").append($tarefa);
		}
		$(".tarefa-delete").click(onTarefaDeleteClick);
		$(".tarefa-item").click(onTarefaItemClick);
		$(".tarefa-concluir").click(onTarefaConcluirClick);
		
		if(id === 0) {
			var div = $($tarefa.children(".tarefa-id"));
			console.log("id", div);
			novaTarefa(text, $(div));
		}
	}

	//incluir nova tarefa quando apertar ENTER
	function onTarefaKeydown(event) {
		if(event.which === 13) {
			addTarefa($("#tarefa").val());
			$("#tarefa").val("");
		}
	}

	//editar tarefa quando apertar ENTER
	function onTarefaEditKeydown(event) {
		if(event.which === 13) {
			savePendingEdition($lastClicked);
			$lastClicked = undefined;
		}
	}

	//permite a alteração quando clicar numa tarefa
	function onTarefaItemClick() {
		if(!$(this).is($lastClicked)) {
			if($lastClicked !== undefined) {
				savePendingEdition($lastClicked);
			}
			$lastClicked = $(this);
			var text = $lastClicked.children('.tarefa-text').text();
			var id = $lastClicked.children('.tarefa-id').text();
			console.log("onTarefaClick  id: ", id);
			
			var content = 
			"<input type='text' class='tarefa-edit' value='" + text + "'>" +
			"<div class='tarefa-id'>" + id + "</div>";
			$lastClicked.html(content);
			$(".tarefa-edit").keydown(onTarefaEditKeydown);
		}
	}

	//salvar edição das tarefas
	function savePendingEdition($tarefa) {
		var id = $tarefa.children('.tarefa-id').text();
		var text = $tarefa.children('.tarefa-edit').val();
		
		$tarefa.empty();
		$tarefa
		.append("<div class='tarefa-text'>" + text + "</div>")
		.append("<div class='tarefa-concluir'></div>")
		.append("<div class='tarefa-delete'></div>")
		.append("<div class='tarefa-id'>" + id + "</div>");
		console.log("text: ",text);
		console.log("id: ",id);
		atualizarTarefa(text, id);
		
		$(".tarefa-delete").click(onTarefaDeleteClick);
		$tarefa.click(onTarefaItemClick);
	}

	function carregarTarefas() {
		$("#tarefa").empty();
		$.getJSON(server + "/CarregarTarefas")
		.done(function(data) {
		console.log("data: ", data);
		for(var tarefa = 0; tarefa < data.length; tarefa++) {
			console.log("ENTROU NO FOR", tarefa);
		addTarefa(data[tarefa].nome,data[tarefa].idTarefa,data[tarefa].status);
		}
		});
	}
	
	//Atualizar Tarefa no banco
	function atualizarTarefa(text, id) {
		$.post(server + "/AtualizarTarefa", {tarefa_id: id, texto: text});
	}
	
	//Adicionar Tarefa no banco
	function novaTarefa(text, $div) {
		$.post(server + "/AdiconarTarefa",
				{nome: text})
				.done(function(data) {
					$div.text(data);
				});
	}
	

	//callbacks dos eventos
	$(".tarefa-concluir").click(onTarefaConcluirClick);
	$(".tarefa-delete").click(onTarefaDeleteClick);
	$(".tarefa-item").click(onTarefaItemClick);
	$("#tarefa").keydown(onTarefaKeydown);
	
	
	carregarTarefas();



});