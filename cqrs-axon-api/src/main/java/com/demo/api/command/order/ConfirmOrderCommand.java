package com.demo.api.command.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * <p>
 *
 * </p>
 *
 * @author wangliang
 * @since 2019/11/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private Long id;
}
