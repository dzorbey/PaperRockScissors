package com.restapi.integration.command;

import com.beust.jcommander.Parameter;

public class BaseCommand {
  @Parameter(names = "-help", help = true)
  private boolean help;

  public boolean getHelp() {
    return help;
  }

  public void setHelp(boolean help) {
    this.help = help;
  }


}
