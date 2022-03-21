package br.com.cardoso.gabriel.starwarsapi.starwarsapi.service;

import br.com.cardoso.gabriel.starwarsapi.starwarsapi.exception.*;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Rebel;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.model.Report;
import br.com.cardoso.gabriel.starwarsapi.starwarsapi.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final RebelService rebelService;

    public Report reportRebel(Report report){
        if (report.getAccuser().equals(report.getAccused())) {
            throw new SelfReportException();
        }
        Integer accuserId = report.getAccuser().getId();
        Integer accusedId = report.getAccused().getId();

        if (reportRepository.existsByAccuserIdAndAccusedId(accuserId, accusedId)){
            throw new ReportAlreadyExistsException();
        }
        Report reportSaved = reportRepository.save(report);
        Rebel rebelReported = rebelService.getById(report.getAccused().getId());
        //Inventory rebelReportedInventory = rebelReported.getInventory();
        if (rebelReported.getReportedList().size() >= 3) {
            //rebelReportedInventory.setBlocked(true);
            //rebelReported.setInventory(rebelReportedInventory);
            rebelReported.setBetrayer(true);
            rebelService.save(rebelReported);
        }
        return reportSaved;
    }
}
