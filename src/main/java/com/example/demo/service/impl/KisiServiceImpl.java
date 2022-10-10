package com.example.demo.service.impl;

import com.example.demo.dto.KisiDto;
import com.example.demo.entity.Adres;
import com.example.demo.entity.Kisi;
import com.example.demo.repo.AdresRepository;
import com.example.demo.repo.KisiRepository;
import com.example.demo.service.KisiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KisiServiceImpl implements KisiService {

    private final KisiRepository kisiRepository;
    private final AdresRepository adresRepository;
    @Override
    @Transactional
    public KisiDto save(KisiDto kisiDto) {
        Kisi kisi = new Kisi();
        kisi.setAdi(kisiDto.getAdi());
        kisi.setSoyadi(kisiDto.getSoyadi());
        final Kisi kisiDb = kisiRepository.save(kisi);
        List<Adres> liste = new ArrayList<>();

        kisiDto.getAdresler().forEach(item -> {
            Adres adres = new Adres();
            adres.setAdres(item);
            adres.setAdresTip(Adres.AdresTip.DEGER);;
            adres.setAktif(true);
            adres.setKisi(kisi);
            liste.add(adres);
        });
        adresRepository.saveAll(liste);
        kisiDto.setId(kisiDb.getId());
        return kisiDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public KisiDto getAll(KisiDto kisiDto) {
        return null;
    }

    @Override
    public Page<KisiDto> getAll(Pageable pageable) {
        return null;
    }
}
