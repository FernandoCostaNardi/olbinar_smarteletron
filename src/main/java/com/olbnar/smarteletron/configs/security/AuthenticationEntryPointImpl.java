package com.olbnar.smarteletron.configs.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olbnar.smarteletron.dtos.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Log4j2
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorizes");
//        String mensagemErro = "Acesso não autorizado. Por favor, faça login para continuar.";
//
//        // Escreva a mensagem de erro na resposta
//        PrintWriter writer = response.getWriter();
//        writer.println(mensagemErro);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Defina o tipo de conteúdo da resposta para indicar uma resposta JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        // Crie uma mensagem de erro personalizada
        String mensagemErro = "Acesso não autorizado. Por favor, faça login para continuar.";

        // Crie um objeto JSON de erro
        ErrorDto erroJson = new ErrorDto("Authorization Error", mensagemErro);

        // Serialize o objeto JSON na resposta
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(erroJson);

        // Escreva o JSON na resposta
        PrintWriter writer = response.getWriter();
        writer.println(json);

    }
}
