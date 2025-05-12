package com.Chamados.SalesBud.demo.services.implementacao;

import com.Chamados.SalesBud.demo.bean.DTO.ChamadosBudDTO;
import com.Chamados.SalesBud.demo.bean.DTO.EmpresaDto;
import com.Chamados.SalesBud.demo.bean.DTO.UsuarioDto;
import com.Chamados.SalesBud.demo.bean.entity.ChamadosBud;
import com.Chamados.SalesBud.demo.bean.entity.Empresa;
import com.Chamados.SalesBud.demo.bean.entity.Usuario;
import com.Chamados.SalesBud.demo.bean.enunPackage.ClassificaChamado;
import com.Chamados.SalesBud.demo.bean.enunPackage.StatusChamado;
import com.Chamados.SalesBud.demo.exception.EmpresaNotFound;
import com.Chamados.SalesBud.demo.repository.ChamadosBudRepository;
import com.Chamados.SalesBud.demo.repository.EmpresaRepository;
import com.Chamados.SalesBud.demo.services.ChamadoService;
import com.Chamados.SalesBud.demo.view.chamadoView.ChamadoView;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ChamadosServiceImpl implements ChamadoService {

    @Autowired
    ChamadosBudRepository chamadosBudRepository;

    @Autowired
    EmpresaRepository empresaRepository;


    @Override
    public boolean registrarChamado(int idChamado, int status, String descricao, UsuarioDto usuario) {
        LocalDate dataHoje = LocalDate.now();
        LocalTime horaAgora = LocalTime.now();
        Date dataSql = Date.valueOf(dataHoje);
        Time horaSql = Time.valueOf(horaAgora);
        Long idEmpresa = (long) idChamado;
        Optional<Empresa> empresa = null;
        ClassificaChamado chamadoClassifca = switch (status) {
            case 1 -> ClassificaChamado.GRAVE;
            case 2 -> ClassificaChamado.NAO_GRAVE;
            default -> throw new IllegalArgumentException("Status inválido: " + status);
        };

            empresa = empresaRepository.findById(idEmpresa);

       ChamadosBud chamadosBud = new ChamadosBud(dataSql,horaSql,StatusChamado.Aberto,descricao,chamadoClassifca,empresa.get(),new Usuario(usuario));

       ChamadosBud chamadosBud1 = chamadosBudRepository.save(chamadosBud);
       return chamadosBud1 != null;
    }

    @Override
    public List<ChamadosBud> listarChamados(int idEmpresa) {
        Empresa empresaDto = empresaRepository.findById((long)idEmpresa).orElseThrow();
        List<ChamadosBud> chamadosBudList = chamadosBudRepository.findAll();
        List<ChamadosBud> chamadosDaEmpresa = chamadosBudList.stream()
                .filter(chamado -> chamado.getEmpresaChamado().equals(empresaDto))
                .collect(Collectors.toList());
        return chamadosDaEmpresa;
    }

    @Override
    public List<ChamadosBud> listarChamados() {
        return chamadosBudRepository.findAll();
    }

    @Override
    public void fecharChamdo(int idChamado) {
        ChamadosBud chamadosBud = chamadosBudRepository.findById((long) idChamado)
                .orElseThrow(() -> new EntityNotFoundException("Chamado não encontrado"));

        chamadosBud.setStatusChamado(StatusChamado.Resolvido);
        chamadosBudRepository.save(chamadosBud);
    }

    @Override
    public void exportarCsvBDCompleto() {
        List<ChamadosBud> chamadosBudList = chamadosBudRepository.findAll();
        String csvPath = "todosChamados.csv";

        try (
                FileWriter writer = new FileWriter(csvPath);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Data", "Hora", "Status", "Observações", "Classificação", "Empresa", "Usuário"))
        ) {
            for (ChamadosBud chamado : chamadosBudList) {
                csvPrinter.printRecord(
                        chamado.getIdChamados(),
                        chamado.getDataChamado(),
                        chamado.getHoraChamado(),
                        chamado.getStatusChamado(),
                        chamado.getCampoObsChamado(),
                        chamado.getClassificaChamado(),
                        chamado.getEmpresaChamado().getEmpresaNome(),
                        chamado.getUsuarioChamado().getUserName()
                );
            }

            csvPrinter.flush();
            System.out.println("CSV GERADO COM SUCESSO");

        } catch ( IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }

    @Override
    public void exportarCsvSoComUmaEmpresa(int idEmpresa) {
        Empresa empresaDto = empresaRepository.findById((long)idEmpresa).orElseThrow();
        List<ChamadosBud> chamadosBudList = chamadosBudRepository.findAll();
        List<ChamadosBud> chamadosBudsFiltrado = chamadosBudList.stream()
                .filter(chamado -> chamado.getEmpresaChamado().equals(empresaDto)).toList();

        String caminho = "chamados"+empresaDto.getEmpresaNome()+".csv";
        try (
                FileWriter writer = new FileWriter(caminho);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Data", "Hora", "Status", "Observações", "Classificação", "Empresa", "Usuário"))
        ) {
            for (ChamadosBud chamado : chamadosBudsFiltrado) {
                csvPrinter.printRecord(
                        chamado.getIdChamados(),
                        chamado.getDataChamado(),
                        chamado.getHoraChamado(),
                        chamado.getStatusChamado(),
                        chamado.getCampoObsChamado(),
                        chamado.getClassificaChamado(),
                        chamado.getEmpresaChamado().getEmpresaNome(),
                        chamado.getUsuarioChamado().getUserName()
                );
            }

            csvPrinter.flush();
            System.out.println("CSV GERADO COM SUCESSO");

        } catch ( IOException e) {
            System.err.println("Erro ao exportar CSV: " + e.getMessage());
        }
    }
}
