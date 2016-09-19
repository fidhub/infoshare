package infoshare.app.util.fields;

import com.vaadin.data.util.converter.AbstractStringToNumberConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by hashcode on 2015/09/07.
 */
public class BigDecimalConverter extends AbstractStringToNumberConverter<BigDecimal> {
    @Override
    protected NumberFormat getFormat(Locale locale) {
        NumberFormat numberFormat = super.getFormat(locale);
        if (numberFormat instanceof DecimalFormat) {
            ((DecimalFormat) numberFormat).setParseBigDecimal(true);
        }

        return numberFormat;
    }

    @Override
    public BigDecimal convertToModel(String value,
                                     Class<? extends BigDecimal> targetType, Locale locale)
            throws ConversionException {
        return (BigDecimal) convertToNumber(value, BigDecimal.class, locale);
    }

    @Override
    public Class<BigDecimal> getModelType() {
        return BigDecimal.class;
    }
}
