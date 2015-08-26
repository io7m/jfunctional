/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
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

import java.io.Serializable;

/**
 * <p> The type of optional values. </p>
 *
 * @param <T> The type of values.
 */

@EqualityStructural public interface OptionType<T> extends Serializable
{
  /**
   * <p> If this value is <code>Some(x)</code>, evaluate {@code p(x)}.
   * Otherwise, do nothing. </p>
   *
   * @param p The procedure
   *
   * @since 1.1.0
   */

  void map_(final ProcedureType<T> p);

  /**
   * <p> If this value is <code>Some(x)</code>, evaluate {@code p(x)}.
   * Otherwise, do nothing. </p>
   *
   * @param p The procedure The type of exceptions thrown by the procedure.
   *
   * @since 1.1.0
   */

  <E extends Throwable> void mapPartial_(
    final PartialProcedureType<T, E> p)
    throws E;

  /**
   * Accept a visitor.
   *
   * @param v   The visitor
   * @param <U> The type of values returned by the visitor.
   *
   * @return The value returned by the visitor.
   */

  <U> U accept(
    final OptionVisitorType<T, U> v);

  /**
   * Accept a partial visitor.
   *
   * @param v   The visitor
   * @param <U> The type of values returned by the visitor.
   * @param <E> The type of exceptions thrown by the visitor.
   *
   * @return The value returned by the visitor.
   *
   * @throws E If the visitor throws <code>E</code>.
   */

  <U, E extends Throwable> U acceptPartial(
    final OptionPartialVisitorType<T, U, E> v)
    throws E;

  /**
   * @return <code>true</code> if the current value is {@link None}.
   */

  boolean isNone();

  /**
   * @return <code>true</code> if the current value is {@link Some}.
   */

  boolean isSome();

  /**
   * <p> If this value is <code>Some(x)</code>, return {@link Option#of(Object)}
   * with <code>f(x)</code>. Otherwise, return {@link None}. </p>
   *
   * @param <U> The type of returned values.
   * @param f   The map function.
   *
   * @return An optional value of type <code>U</code>.
   */

  <U> OptionType<U> map(
    FunctionType<T, U> f);

  /**
   * <p> If this value is <code>Some(x)</code>, return {@link Option#of(Object)}
   * with <code>f(x)</code>. Otherwise, return {@link None}. </p>
   *
   * @param <E> The type of exceptions raised.
   * @param <U> The type of returned values.
   * @param f   The map function.
   *
   * @return An optional value of type <code>U</code>.
   *
   * @throws E If <code>f</code> throws <code>E</code>.
   */

  <U, E extends Throwable> OptionType<U> mapPartial(
    PartialFunctionType<T, U, E> f)
    throws E;
}
