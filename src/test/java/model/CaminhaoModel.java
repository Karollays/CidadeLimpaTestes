package model;
import com.google.gson.annotations.Expose;
import lombok.Data;
@Data
public class CaminhaoModel {
    @Expose(serialize = false)
    private int numeroCaminhao;
    @Expose
    private String registro;
    @Expose
    private String capacidade;
}