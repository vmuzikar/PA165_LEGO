package org.legomanager.api.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Representation of merging categories
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class CategoryMergeDto {
    @NotNull(message = "Must be selected.")
    private Long targetId;

    @NotEmpty(message = "At least one must be selected.")
    private List<Long> mergeWithIds;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public List<Long> getMergeWithIds() {
        return mergeWithIds;
    }

    public void setMergeWithIds(List<Long> mergeWithIds) {
        this.mergeWithIds = mergeWithIds;
    }
}
