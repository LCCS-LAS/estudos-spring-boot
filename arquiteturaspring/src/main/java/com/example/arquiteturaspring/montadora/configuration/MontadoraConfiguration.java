package com.example.arquiteturaspring.montadora.configuration;

import com.example.arquiteturaspring.montadora.Motor;
import com.example.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado(){
        var motor = new Motor();
        motor.setCavalo(120);
        motor.setCilindros(4);
        motor.setModelo("H2W");
        motor.setLitragem(2.0);
        motor.setTipoMotor(TipoMotor.ASPIRADO);
        return motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico(){
        var motor = new Motor();
        motor.setCavalo(300);
        motor.setCilindros(0);
        motor.setModelo("B67P");
        motor.setLitragem(0.0);
        motor.setTipoMotor(TipoMotor.ELETRICO);
        return motor;
    }

    @Primary
    @Bean(name = "motorTurbo")
    public Motor motorTurbo(){
        var motor = new Motor();
        motor.setCavalo(400);
        motor.setCilindros(6);
        motor.setModelo("H2W6X");
        motor.setLitragem(4.2);
        motor.setTipoMotor(TipoMotor.TURBO);
        return motor;
    }
}
