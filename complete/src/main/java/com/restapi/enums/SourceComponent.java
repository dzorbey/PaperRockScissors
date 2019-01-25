package com.restapi.enums;

import com.google.common.base.Joiner;
import com.restapi.exception.IllegalSourceException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public enum SourceComponent {
	  TESTING, GAME, @ApiModelProperty(hidden = true)
	  UNIT_TEST;

	  public boolean equals(String o) {
	    return toString().equals(o);
	  }

	  public void validate(SourceComponent... allowedSources) throws IllegalSourceException {
	    for (SourceComponent allowedSource : allowedSources) {
	      if (this.equals(allowedSource))
	        return;
	    }
	    throw new IllegalSourceException("Illegal source " + this + " expected either of "
	        + Joiner.on(", ").join(allowedSources));
	  }


	}