package domain;

import util.LineItemGenerator;
import view.LineItem;
import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<LineItem> lineItems;
    private final int columnLength;

    public Line(int columnLength) {
        this.lineItems = new ArrayList<>();
        this.columnLength = columnLength;
    }

    public List<LineItem> makeLine(LineItemGenerator lineItemGenerator) {
        for (int position = 0; position < columnLength - 1; position++) {
            LineItem lineItem = lineItemGenerator.generate();

            lineItems.add(decideLineItem(position, lineItem));
        }

        return lineItems;
    }

    public LineItem decideLineItem(int position, LineItem lineItem) {
        if (position == 0 || !LineItem.isConnected(lineItems.get(position - 1))) {
            return lineItem;
        }

        return LineItem.UNCONNECTED;
    }

    public int move(int position) {
        if (position < lineItems.size() && LineItem.isConnected(lineItems.get(position))) {
            return position + 1;
        }

        if (position > 0 && LineItem.isConnected(lineItems.get(position - 1))) {
            return position - 1;
        }

        return position;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
