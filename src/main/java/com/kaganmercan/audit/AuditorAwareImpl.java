package com.kaganmercan.audit;

import com.kaganmercan.business.dto.DailyDto;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Kağan Mercan");
    }
}
