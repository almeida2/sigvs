package com.fatec.sigvs.view;

import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

@UsePlaywright
public class TSReq09CadastrarClienteTests {
    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void test(Page page) {
        page.navigate("http://localhost:5173/");
        logger.info(">>>>>> navegando da tela menu para tela de cadastro de cliente");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cadastrar cliente")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("000.000.000-")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("000.000.000-")).fill("19894261078");
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
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("evidencia_cadastro_cliente.png")));
    }
}
