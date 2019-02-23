package my.spring.project.springmvc.config.jsp;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JspConfigDescriptorImpl implements JspConfigDescriptor {
    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return Collections.emptyList();
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        final JspPropertyGroupDescriptorImpl descriptor = new JspPropertyGroupDescriptorImpl();
        descriptor.setDefaultContentType("text/html; charset=UTF-8");
        descriptor.setPageEncoding("UTF-8");
        descriptor.setUrlPatterns(Collections.singletonList("*.jsp"));

        final List<JspPropertyGroupDescriptor> descriptors = new ArrayList<>();
        descriptors.add(descriptor);

        return descriptors;
    }
}