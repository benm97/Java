package filters;

import java.io.File;


class FileFilter extends Filter {

    private boolean inverse;
    private String value;

    FileFilter(String valueWithPrefix, boolean inverse) {
        this.value = valueWithPrefix;
        this.inverse = inverse;
    }

    boolean filterCheck(File file) {
        if (inverse)
            return !value.equals(file.getName());
        else
            return value.equals(file.getName());

    }


}
