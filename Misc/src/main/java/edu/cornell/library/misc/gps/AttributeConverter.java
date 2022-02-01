package edu.cornell.library.misc.gps;

import java.util.Collection;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.core.util.HierarchicalStreams;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper; 
 
public class AttributeConverter extends CollectionConverter {

    private final String name;
    private final Class<?> type;

    /**
     * Constructs a AttributeConverter.
     * 
     * @param mapper the mapper
     * @param itemName the name of the items
     * @param itemType the base type of the items
     * @since 1.4.5
     */
    public AttributeConverter(final Mapper mapper, final String itemName, final Class<?> itemType) {
        this(null, mapper, itemName, itemType);
    }

    /**
     * Constructs a AttributeConverter handling an explicit Collection type.
     * 
     * @param type the Collection type to handle
     * @param mapper the mapper
     * @param itemName the name of the items
     * @param itemType the base type of the items
     * @since 1.4.5
     */
    public AttributeConverter(
            @SuppressWarnings("rawtypes") final Class<? extends Collection> type, final Mapper mapper,
            final String itemName, final Class<?> itemType) {
        super(mapper, type);
        name = itemName;
        this.type = itemType;
    }

    @Override
    protected void writeItem(final Object item, final MarshallingContext context, final HierarchicalStreamWriter writer) {
        final Class<?> itemType = item == null ? Mapper.Null.class : item.getClass();
        ExtendedHierarchicalStreamWriterHelper.startNode(writer, name, itemType);
        if (!itemType.equals(type)) {
            final String attributeName = mapper().aliasForSystemAttribute("class");
            if (attributeName != null) {
                writer.addAttribute(attributeName, mapper().serializedClass(itemType));
            }
        }
        if (item != null) {
            context.convertAnother(item);
        }
        writer.endNode();
    }

    @Override
    protected Object readItem(final HierarchicalStreamReader reader, final UnmarshallingContext context,
            final Object current) {
        final String className = HierarchicalStreams.readClassAttribute(reader, mapper());
        final Class<?> itemType = className == null ? type : mapper().realClass(className);
        if (Mapper.Null.class.equals(itemType)) {
            return null;
        } else {
            return context.convertAnother(current, itemType);
        }
    }
}