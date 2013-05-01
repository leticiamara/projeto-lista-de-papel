package br.ufc.quixada.es.util;

import java.io.IOException;
import java.util.List;

import br.ufc.quixada.es.modelo.Tarefa;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSerializer {

	private static DataSerializer instance;
	ObjectMapper objectMapper = null;

	private DataSerializer() {
		objectMapper = new ObjectMapper();
	}

	public static DataSerializer getInstance() {
		if(instance == null)
			instance = new DataSerializer();

		return instance;
	}

	public String converterParaJson(List<Tarefa> tarefas) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); //ObjectMapper é uma classe da biblioteca Jackson
		return mapper.writeValueAsString(tarefas); //Este metodo irá retornar o JSON da variável "tarefas".
	}

	public Tarefa converterJsonParaTarefa(String json) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Tarefa.class); //Método utilizado para ler o json e retornar a instância da classe.
	}


}
