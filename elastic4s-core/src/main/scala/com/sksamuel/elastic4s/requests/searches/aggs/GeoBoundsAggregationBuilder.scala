package com.sksamuel.elastic4s.requests.searches.aggs

import com.sksamuel.elastic4s.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s.requests.script.ScriptBuilderFn

object GeoBoundsAggregationBuilder {
  def apply(agg: GeoBoundsAggregation): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("geo_bounds")

    agg.field.foreach(builder.field("field", _))
    agg.format.foreach(builder.field("format", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.wrapLongitude.foreach(builder.field("size", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }

    builder.endObject()

    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
