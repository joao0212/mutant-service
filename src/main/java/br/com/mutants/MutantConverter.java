package br.com.mutants;

import br.com.exception.MutantNotFoundException;
import com.datastax.driver.core.Row;

public class MutantConverter {

    public static Mutant convert(Row row) {
        if(row != null) {
            return new Mutant(row.getInt(0), row.getString(1), row.getInt(2));
        } else {
            throw new MutantNotFoundException();
        }
    }
}
