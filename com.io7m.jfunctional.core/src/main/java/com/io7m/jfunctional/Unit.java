/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jfunctional;

import com.io7m.jequality.annotations.EqualityStructural;
import com.io7m.jnull.Nullable;

import java.io.Serializable;

/**
 * <p>
 * The single-valued unit type.
 * </p>
 */

@EqualityStructural
public final class Unit implements Serializable
{
  /**
   * The hash code for the {@link Unit} type.
   */

  public static final int UNIT_HASH_CODE = 0xFACECAFE;
  private static final long serialVersionUID = -9037394631765247295L;
  private static final Unit UNIT = new Unit();

  private Unit()
  {

  }

  /**
   * @return The unit value
   */

  public static Unit unit()
  {
    return Unit.UNIT;
  }

  @Override
  public boolean equals(
    final @Nullable Object obj)
  {
    return this == obj || obj != null && this.getClass() == obj.getClass();
  }

  @Override
  public int hashCode()
  {
    return Unit.UNIT_HASH_CODE;
  }
}
