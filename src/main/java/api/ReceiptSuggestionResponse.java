package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * Represents the result of an OCR parse
 */
public class ReceiptSuggestionResponse {
    @JsonProperty
    public final String merchant;

    @JsonProperty
    public final BigDecimal amount;


    public ReceiptSuggestionResponse(String merchant, BigDecimal amount) {
        this.merchant = merchant;
        this.amount = amount;
    }
}
