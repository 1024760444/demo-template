package com.yhaitao.mahout.kmeans;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * IK分词，统计词频。
 * @author yhaitao
 *
 */
public class KmeansIKAnalyzer {
	/**
	 * 分词器。
	 */
	private Analyzer analyzer;
	
	/**
	 * 初始化IK分词器。
	 */
	public KmeansIKAnalyzer() {
		this.analyzer = new IKAnalyzer(true);
	}
	
	/**
	 * 对输入文本使用IK分词器分词， 统计词频。
	 * @param context 需要分词的文本
	 * @return 输入文本的词频统计
	 * @throws IOException 
	 */
	public Map<String, Double> analyzer(String context) throws IOException {
		// 构造文本输入流，构造分词器。
		StringReader reader = new StringReader(context);
		TokenStream tokenStream = analyzer.tokenStream("", reader);
		CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
		
		// 遍历分词数据
		Map<String, Double> analyzerMap = new HashMap<String, Double>();
		while (tokenStream.incrementToken()) {
			String key = term.toString();
			/** 权重累加 **/
			if(analyzerMap.containsKey(key)) {
				analyzerMap.put(key, analyzerMap.get(key) + 1);
			} else {
				analyzerMap.put(key, Double.valueOf(1));
			}
		}
		
		reader.close();
		tokenStream.close();
		return analyzerMap;
	}
	
	/**
	 * 关闭IK分词器。
	 */
	public void close() {
		if(analyzer != null) {
			analyzer.close();
		}
	}
}
