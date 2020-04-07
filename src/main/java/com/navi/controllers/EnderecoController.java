package com.navi.controllers;

import com.navi.config.DatabaseConfig;
import com.navi.models.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class EnderecoController {

    Endereco endereco;

    DatabaseConfig database = new DatabaseConfig();

    @PostMapping("/cadastro/endereco")
    public Long create(Endereco novoEndereco) throws SQLException {
        String insert = String.format("INSERT INTO public.endereco(\n\t n_cep, logradoruro, bairro, localidade, uf, numero, complemento)\n\tVALUES (?, ?, ?, ?, ?, ?, ?);");
        long id = 0;

        try ( PreparedStatement preparedStatement = database.connect().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            {
                preparedStatement.setInt(1, novoEndereco.getN_cep());
                preparedStatement.setString(2, novoEndereco.getLogradouro());
                preparedStatement.setString(3, novoEndereco.getBairro());
                preparedStatement.setString(4, novoEndereco.getLocalidade());
                preparedStatement.setString(5, novoEndereco.getUf());
                preparedStatement.setInt(6, novoEndereco.getNumero());
                preparedStatement.setString(7, novoEndereco.getComplememnto());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            id = resultSet.getLong(1);
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

}
