package app;

import static spark.Spark.*;

import service.pessoasService;

public class principal {
	
	private static pessoasService pessoasService = new pessoasService();
	
    public static void main(String[] args) {
    	port(5432);

        post("/pessoas", (request, response) -> pessoasService.add(request, response));

        get("/pessoas/:codigo", (request, response) -> pessoasService.get(request, response));

        get("/pessoas/update/:codigo", (request, response) -> pessoasService.update(request, response));

        get("/pessoas/delete/:codigo", (request, response) -> pessoasService.remove(request, response));

        get("/pessoas", (request, response) -> pessoasService.getAll(request, response));
    }
}
