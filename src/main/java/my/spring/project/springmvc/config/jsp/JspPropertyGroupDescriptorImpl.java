package my.spring.project.springmvc.config.jsp;

import lombok.Setter;

import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import java.util.Collection;

@Setter
public class JspPropertyGroupDescriptorImpl implements JspPropertyGroupDescriptor {
    private Collection<String> urlPatterns;
    private String elIgnored;
    private String pageEncoding;
    private String scriptingInvalid;
    private String isXml;
    private Collection<String> includePreludes;
    private Collection<String> includeCodas;
    private String deferredSyntaxAllowedAsLiteral;
    private String trimDirectiveWhitespaces;
    private String defaultContentType;
    private String buffer;
    private String errorOnUndeclaredNamespace;

    @Override
    public Collection<String> getUrlPatterns() {
        return urlPatterns;
    }

    @Override
    public String getElIgnored() {
        return elIgnored;
    }

    @Override
    public String getPageEncoding() {
        return pageEncoding;
    }

    @Override
    public String getScriptingInvalid() {
        return scriptingInvalid;
    }

    @Override
    public String getIsXml() {
        return isXml;
    }

    @Override
    public Collection<String> getIncludePreludes() {
        return includePreludes;
    }

    @Override
    public Collection<String> getIncludeCodas() {
        return includeCodas;
    }

    @Override
    public String getDeferredSyntaxAllowedAsLiteral() {
        return deferredSyntaxAllowedAsLiteral;
    }

    @Override
    public String getTrimDirectiveWhitespaces() {
        return trimDirectiveWhitespaces;
    }

    @Override
    public String getDefaultContentType() {
        return defaultContentType;
    }

    @Override
    public String getBuffer() {
        return buffer;
    }

    @Override
    public String getErrorOnUndeclaredNamespace() {
        return errorOnUndeclaredNamespace;
    }
}