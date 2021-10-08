package service;


import dao.dao;
import model.PessoasDAO;
import spark.Request;
import spark.Response;

public class pessoasService {

	private dao dao;//ProdutoDAO produtoDAO

	/*
	public pessoasService() {
		try {
			pessoasDAO = new PessoasDAO("produto.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	*/

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		char sexo = (request.queryParams("sexo")).charAt(0);
		int altura = Integer.parseInt(request.queryParams("altura"));
		String ocupacao = request.queryParams("ocupacao");

		int codigo = dao.getMaxId() + 1;

		PessoasDAO pessoas = new PessoasDAO(codigo, nome, sexo, altura, ocupacao);

		dao.add(pessoas);

		response.status(201); // 201 Created
		return codigo;
	}

	public Object get(Request request, Response response) {
		int codigo = Integer.parseInt(request.params(":codigo"));
		
		PessoasDAO pessoas = (PessoasDAO) dao.get(codigo);
		
		if (pessoas != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<pessoas>\n" + 
            		"\t<codigo>" + pessoas.getCodigo() + "</codigo>\n" +
            		"\t<nome>" + pessoas.getNome() + "</nome>\n" +
            		"\t<sexo>" + pessoas.getSexo() + "</sexo>\n" +
            		"\t<altura>" + pessoas.getAltura() + "</altura>\n" +
            		"\t<ocupacao>" + pessoas.getOcupacao() + "</ocupacao>\n" +
            		"</pessoas>\n";
        } else {
            response.status(404); // 404 Not found
            return "Pessoa " + codigo + " não encontrada.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		PessoasDAO pessoas = (PessoasDAO) dao.get(id);

        if (pessoas != null) {
        	pessoas.setNome(request.queryParams("nome"));
        	pessoas.setSexo(request.queryParams("sexo").charAt(0));
        	pessoas.setAltura(Integer.parseInt(request.queryParams("altura")));
        	pessoas.setOcupacao(request.queryParams("ocupacao"));

        	dao.update(pessoas);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Pessoa não encontrada.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        PessoasDAO pessoas = (PessoasDAO) dao.get(id);

        if (pessoas != null) {

            dao.remove(pessoas);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Pessoa não encontrada.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (PessoasDAO pessoas : dao.getAll()) {
			returnValue.append("\n<pessoas>\n" + 
            		"\t<codigo>" + pessoas.getCodigo() + "</codigo>\n" +
            		"\t<nome>" + pessoas.getNome() + "</nome>\n" +
            		"\t<sexo>" + pessoas.getSexo() + "</sexo>\n" +
            		"\t<altura>" + pessoas.getAltura() + "</altura>\n" +
            		"\t<ocupacao>" + pessoas.getOcupacao() + "</ocupacao>\n" +
            		"</produto>\n");
		}
		returnValue.append("</pessoas>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}

}
