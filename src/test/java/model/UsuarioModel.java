package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {
    @Expose(serialize = false)
    private int id;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String senha;
    @Expose
    private String role;
}