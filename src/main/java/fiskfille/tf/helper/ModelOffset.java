package fiskfille.tf.helper;

/**
 * @author gegy1000
 */
public class ModelOffset
{
    public float headOffsetY = 0;
    public float headOffsetX = 0;
    public float headOffsetZ = 0;
    
    private final boolean initialized;
    
    public ModelOffset(boolean init)
    {
        initialized = init;
    }
    
    public boolean isInitialized()
    {
        return initialized;
    }
}
