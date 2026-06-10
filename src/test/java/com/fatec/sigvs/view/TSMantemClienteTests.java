package com.fatec.sigvs.view;

import com.microsoft.playwright.junit.UsePlaywright;
import com.fatec.sigvs.model.IClienteRepository;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@UsePlaywright
public class TSMantemClienteTests {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    IClienteRepository repository;

    // @Test
    void ct01_cadastrar_cliente_com_sucesso(Page page) {
        // Gera um CPF único e válido para esta execução
        String cpfAleatorio = CpfGenerator.generateValidCpf();
        logger.info(">>>>>> Iniciando teste com o CPF: " + cpfAleatorio);
        page.navigate("https://cliente-frontv1-1632e1703d87.herokuapp.com/");
        logger.info(">>>>>> navegando da tela menu para tela de cadastro de cliente");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cadastrar cliente")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("000.000.000-")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("000.000.000-")).fill(cpfAleatorio);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("000.000.000-")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Digite o nome completo"))
                .fill("Jose da Silva");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Digite o nome completo")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("-000")).fill("03694000");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("-000")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email@exemplo.com"))
                .fill("jose@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email@exemplo.com")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Apto, Bloco, etc.")).fill("apto 1");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cadastrar")).click();
        logger.info(">>>>>> verificando o resultado do teste");
        assertThat(page.getByText("Cliente cadastrado com sucesso")).isVisible();
        ScreenShotUtil.takeScreenshot(page, "ct01_cadastrar_cliente_com_sucesso");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Voltar")).click();
        page.close();
    }

    // @Test
    void ct02_consultar_cliente(Page page) {
        // dado que o cliente cpf 39702670055 esta cadastrado
        page.navigate("https://cliente-frontv1-1632e1703d87.herokuapp.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Consultar cliente por CPF")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("CPF:")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("CPF:")).fill("50392798921");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Consultar")).click();
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Nome:"))).isVisible();
        assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Nome:")))
                .hasValue("Isabela Jorge");

        ScreenShotUtil.takeScreenshot(page, "ct02_consultar_cliente_com_sucesso");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Voltar")).click();
        page.close();
    }

    // @Test
    void test(Page page) {
        page.navigate("https://cliente-frontv1-1632e1703d87.herokuapp.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Excluir cliente por CPF")).click();
        page.getByTestId("excluir-cpf-input").click();
        page.getByTestId("excluir-cpf-input").fill("21818191229");
        page.onceDialog(dialog -> {
            System.out.println(String.format("Dialog message: %s", dialog.message()));
            dialog.dismiss();
        });
        page.getByTestId("excluir-cliente-button").click();
        assertThat(page.getByRole(AriaRole.STATUS)).containsText("Cliente excluído com sucesso.");
    }
}
