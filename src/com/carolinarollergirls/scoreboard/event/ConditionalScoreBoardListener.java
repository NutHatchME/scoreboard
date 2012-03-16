package com.carolinarollergirls.scoreboard.event;
/**
 * Copyright (C) 2008-2012 Mr Temper <MrTemper@CarolinaRollergirls.com>
 *
 * This file is part of the Carolina Rollergirls (CRG) ScoreBoard.
 * The CRG ScoreBoard is licensed under either the GNU General Public
 * License version 3 (or later), or the Apache License 2.0, at your option.
 * See the file COPYING for details.
 */

import java.util.*;

public class ConditionalScoreBoardListener implements ScoreBoardListener
{
  public ConditionalScoreBoardListener(Class c, String id, String prop, Object v, ScoreBoardListener l) {
    this(new ScoreBoardCondition(c, id, prop, v), l);
  }
  public ConditionalScoreBoardListener(ScoreBoardEventProvider p, String prop, Object v, ScoreBoardListener l) {
    this(new ScoreBoardCondition(p, prop, v), l);
  }
  public ConditionalScoreBoardListener(ScoreBoardEvent e, ScoreBoardListener l) {
    this(new ScoreBoardCondition(e), l);
  }
  public ConditionalScoreBoardListener(ScoreBoardCondition c, ScoreBoardListener l) {
    condition = c;
    listener = l;
  }

  public void scoreBoardChange(ScoreBoardEvent e) {
    if (checkScoreBoardEvent(e))
      matchedScoreBoardChange(e);
  }

  public ScoreBoardListener getScoreBoardListener() { return listener; }

  protected boolean checkScoreBoardEvent(ScoreBoardEvent e) { return condition.equals(e); }
  protected void matchedScoreBoardChange(ScoreBoardEvent e) { getScoreBoardListener().scoreBoardChange(e); }

  protected ScoreBoardCondition condition;
  protected ScoreBoardListener listener;
}

